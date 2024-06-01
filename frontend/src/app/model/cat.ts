import { CatFact } from "./cat-fact";
import { Owner } from "./owner";

export class Cat {
  id?: number;
  name: string;
  age: number;
  facts: CatFact[];
  owners: Owner[];

  constructor(name: string, age: number, facts?: CatFact[], owners?: Owner[]) {
    this.name = name;
    this.age = age;
    this.facts = facts || [];
    this.owners = owners || [];
  }
}