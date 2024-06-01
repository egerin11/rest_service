import { Component, Inject, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Cat } from 'src/app/model/cat';
import { CatFact } from 'src/app/model/cat-fact';
import { Owner } from 'src/app/model/owner';

@Component({
  selector: 'app-dialog-box',
  templateUrl: './dialog-box.component.html',
  styleUrls: ['./dialog-box.component.css']
})
export class DialogBoxComponent implements OnInit {



  myForm: FormGroup = new FormGroup({
    name: new FormControl(''),
    age: new FormControl(),
    facts:new FormArray([new FormControl('')]),
    owners:new FormArray([new FormControl('')])
  });

  constructor(
    public dialogRef: MatDialogRef<DialogBoxComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Cat,
  ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
   get facts(): FormArray {
    return this.myForm.get('facts') as FormArray;
  }
  addFact(): void {
    this.facts.push(new FormControl(''));
  }
  get owners():FormArray{
    return this.myForm.get('owners') as FormArray;
  }
addOwners(): void{
  this.owners.push(new FormControl(''));
}
  removeFact(index: number): void {
    this.facts.removeAt(index);
  }
  removeOwner(index: number): void {
    this.owners.removeAt(index);
  }
  ngOnInit(): void {

  }
  onSubmit(){
    const name = this.myForm.value.name;
    const age = this.myForm.value.age;
    const facts = this.myForm.get('facts') as FormArray;
    const owners = this.myForm.get('owners') as FormArray;
  
    const catFacts: CatFact[] = facts.controls.map(control => new CatFact(control.value));
    const ownersData: Owner[] = owners.controls.map(control => new Owner(control.value));
  
    const cat: Cat = {
      name,
      age,
      facts: catFacts,
      owners: ownersData
    };
  
    this.data = {
      name,
      age,
      facts: catFacts,
      owners: ownersData
    };
    console.log(this.data);
    this.dialogRef.close(this.data);
  }
}
