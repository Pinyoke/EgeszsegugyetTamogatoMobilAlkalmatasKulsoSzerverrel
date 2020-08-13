import { User } from 'app/pages/user/model/user.model';
import { Offer } from 'app/pages/offer/model/offer.model';
import { ContactPoint } from './contact-point.model';

export class Seller {
    id: number;
    name: string;
    sellerAdmin : User[];
    offer : Offer[];
    sellerType: string;
    url: string;
    contactPoint : ContactPoint[]

}
