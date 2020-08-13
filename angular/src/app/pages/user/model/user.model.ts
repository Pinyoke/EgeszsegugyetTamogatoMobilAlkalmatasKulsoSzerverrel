import { Sensitivity } from './sensitivity.model';
import { Role } from './role.model';
import { Seller } from 'app/pages/seller/model/seller.model';

export class User {
    id: number;
    name: string;
    password: string;
    email: string;
    sensitivitys: Sensitivity[];
    role: Role;
    roleName : string;
    seller: Seller;
    sellerName: string;
}
