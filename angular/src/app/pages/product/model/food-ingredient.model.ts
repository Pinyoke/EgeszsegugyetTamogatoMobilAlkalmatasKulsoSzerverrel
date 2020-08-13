import { Product } from './product.model';

export class FoodIngredient {

    id: number;
    product: Product;
    type: string;
    name: string;
    alergen: boolean;

}
