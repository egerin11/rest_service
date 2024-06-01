import { Cat } from "./cat";

    export class Owner {
        id?: number;
        name: string;
        cats?: Cat[];
    
        constructor(name: string,cats?:Cat[]) {
        this.name = name;
        this.cats=cats||[];
        }
    }
        