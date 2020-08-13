import { Seller } from './seller.model';

export class ContactPoint {
    id: number;
    areaServed: string;    
    telephone: string;    
    availableLanguage: string;
    seller: Seller;
}
