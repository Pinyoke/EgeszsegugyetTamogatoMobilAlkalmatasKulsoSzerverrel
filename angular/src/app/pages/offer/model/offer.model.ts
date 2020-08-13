import { Product } from 'app/pages/product/model/product.model';
import { Seller } from 'app/pages/seller/model/seller.model';

export class Offer {

    id: number;
    product : Product;
    seller : Seller;
    url: string;
    price: number;
    productName: string;
 

}
