import { FoodIngredient } from './food-ingredient.model';
import { Offer } from 'app/pages/offer/model/offer.model';
import { Image } from './image.model';

export class Product {

    id: number;
    description: string;
    name: string;
    sku: string;
    gtin13: string;
    offers: Offer[];
    foodIngredients: FoodIngredient[] = [];
    imageUrl : string;
}
