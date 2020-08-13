package com.standalone.standalone.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.standalone.standalone.Entity.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@Service
public class DdCreate implements DbCreateInterface {

    private final ProductService productService;
    private final OfferService offerService;
    private final FoodIngredientService foodIngredientService;
    private final SellerService sellerService;
    private final ContactPointService contactPointService;
    private final AllergenService allergenService;

    public DdCreate(ProductService productService, OfferService offerService, FoodIngredientService foodIngredientService, SellerService sellerService, ContactPointService contactPointService, AllergenService allergenService) {
        this.productService = productService;
        this.offerService = offerService;
        this.foodIngredientService = foodIngredientService;
        this.sellerService = sellerService;
        this.contactPointService = contactPointService;
        this.allergenService = allergenService;
    }

    @Override
    public void create(String dbPath) {
        File dbDir = new File(dbPath);
        File[] categoryDirList = dbDir.listFiles();
        for (File categoryDir: categoryDirList) {
            if(categoryDir.isDirectory()){
                File[] jsonFileList = categoryDir.listFiles();
                for (File jsonFile:jsonFileList) {
                    readProductFromJson(jsonFile, categoryDir);
                }
            }

        }


    }

    private void readProductFromJson(File jsonFile, File categoriDir) {
        if(!jsonFile.getName().matches("(.*)_desc(.*)")){
            Product product = null;
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<Product> typeReference = new TypeReference<Product>() {
            };
            InputStream inputStream = null;

            try {
                inputStream = new FileInputStream(jsonFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            product = createProduct(inputStream,typeReference,mapper);


            //Product insertedProduct = productService.save(product);
            //offerService.save(product.getOffer());

            findDescriptionJson(categoriDir , FilenameUtils.getBaseName(jsonFile.getName()), product);

        }
    }

    public byte[] convertImageByte(URL url) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream is = null;
        try {
            is = url.openStream ();
            byte[] byteChunk = new byte[4096]; // Or whatever size you want to read in at a time.
            int n;

            while ( (n = is.read(byteChunk)) > 0 ) {
                baos.write(byteChunk, 0, n);
            }
            return byteChunk;
        }
        catch (IOException e) {
            System.err.printf ("Failed while reading bytes from %s: %s", url.toExternalForm(), e.getMessage());
            e.printStackTrace ();
            // Perform any other exception handling that's appropriate.
        }
        finally {
            if (is != null) { is.close(); }
        }
        return null;
    }


    public Product createProduct(InputStream inputStream,TypeReference<Product> typeReference, ObjectMapper mapper)
    {
        Product product = null;
        try {
            product = mapper.readValue(inputStream, typeReference);
            for (String imageUrl: product.getImageUrls()) {
                product.setImageUrl(imageUrl);
            }
            List<Offer> offers = new ArrayList<>();
            product.getOffer().setProduct(product);
            offers.add(product.getOffer());
            product.setOffers(offers);

        } catch (Exception e) {
            System.out.println("Hiba az producz mentésében: " + e.getMessage());
        }
          setSellerInContactPoint(product);
          setSellerInProduct(product);

        return product;
    }

    public void  setSellerInContactPoint(Product product){
        //ContactPointnak tudnia kell hogy ki a sellerje??!!
        for (ContactPoint contactPoint: product.getOffer().getSeller().getContactPoint()
        ) {
            contactPoint.setSeller(product.getOffer().getSeller());
        }

    }

    public void   setSellerInProduct(Product product){
        if(sellerService.existSellerByUrl(product.getOffer().getSeller().getUrl()))
        {
            product.getOffer().setSeller(sellerService.getSellerByUrl(product.getOffer().getSeller().getUrl()));

        }else {
            sellerService.save(product.getOffer().getSeller());
            for (ContactPoint contactPoint: product.getOffer().getSeller().getContactPoint()) {
                contactPointService.save(contactPoint);
            }
        }

    }

    private void findDescriptionJson(File caegoryDir, String jsonFileName, Product product) {
        File descTxtFile = new File(caegoryDir, jsonFileName +"_desc.txt");
        String descFileContent = null;
        try {
            descFileContent = new String(Files.readAllBytes(Paths.get(descTxtFile.getPath())));
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true); // never forget this!
            DocumentBuilder builder = factory.newDocumentBuilder();
            String c = "<main>" + descFileContent.replaceAll("\\\\\"", "\"") + "</main>";
            Document doc = builder.parse(IOUtils.toInputStream(c, Charset.forName("UTF-8")));
            Element documentElement = doc.getDocumentElement();

            String allIngredientString = nodeToString(documentElement.getChildNodes().item(1).getChildNodes().item(1).getFirstChild());
            descriptionToIngredient(allIngredientString , product);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static String nodeToString(Node node) throws Exception{
        StringWriter sw = new StringWriter();

        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(node), new StreamResult(sw));

        return sw.toString();
    }


    private void descriptionToIngredient(String allIngredientString, Product product) {

        boolean allergen;

        if(!allIngredientString.contains("(") && !allIngredientString.contains(":") && !allIngredientString.contains("<table>") && !allIngredientString.contains("és") && !allIngredientString.contains("class"))
        {
            System.out.println("BELEEEMEEEGYEEEEE");
            allIngredientString = allIngredientString.substring(3,allIngredientString.length()-6);
            //Mogyoro 2,7%
            //Mogyoro 2
            //7%

           /* allIngredientString = allIngredientString.replaceAll("[0-9]","");
            allIngredientString = allIngredientString.replaceAll("%","");
            allIngredientString = allIngredientString.replaceAll(",,",",");

            */

         //   allIngredientString = allIngredientString.replaceAll("[0-9]?[0-9]?,?[0-9]?[0-9]?%","");

            String[] ingredientStringList = allIngredientString.split(",");

            //System.out.println(product.getId()+":\n");

            for (String ingredient: ingredientStringList) {
                ingredient = ingredient.replaceFirst("\\s+","");
                // System.out.println(ingredient+"\n");
                allergen = false;
                if(ingredient.contains("<b>")){
                    String  allergenToSaveName = ingredient.substring(ingredient.indexOf('>')+1);
                    allergenToSaveName = allergenToSaveName.substring(0,allergenToSaveName.indexOf('<'));
                    allergenToSaveName = allergenToSaveName.replaceFirst("\\s+","");
                    Allergen allergenToSave;

                    if(allergenService.findByName(allergenToSaveName) == null) {
                        allergenToSave = new Allergen();
                        allergenToSave.setName(allergenToSaveName);
                        allergenService.save(allergenToSave);
                    }

                    ingredient =  ingredient.replaceAll("<b>([^<]*)</b>", "$1");
                    ingredient = ingredient.replace("\n", "").replace("\r", "").replace("     "," ");
                    allergen= true;

                }
                foodIngredientCreate(ingredient, allergen, product );
            }
            productService.save(product);
            // System.out.println("-------------");

        }

    }

    private void foodIngredientCreate(String name, boolean allergen, Product product) {
        List<FoodIngredient> foodIngredientList;

        FoodIngredient foodIngredient = new FoodIngredient();
        foodIngredient.setName(name);
        foodIngredient.setAlergen(allergen);

        if(product.getFoodIngredients() == null){
            foodIngredientList = new ArrayList<>();
        }else {
            foodIngredientList = product.getFoodIngredients();
        }


        foodIngredientList.add(foodIngredient);

        product.setFoodIngredients(foodIngredientList);

        foodIngredient.setProduct(product);

     //   foodIngredientService.save(foodIngredient);
    //    productService.save(product);


    }

}
