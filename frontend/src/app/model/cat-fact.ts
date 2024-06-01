import { Cat } from "./cat";

export class CatFact {
    id?: number;
    fact: string;
    cat?: Cat;
  
    constructor(fact: string,cat?:Cat) {
      this.fact = fact;
      this.cat=cat;
    }
  }
  