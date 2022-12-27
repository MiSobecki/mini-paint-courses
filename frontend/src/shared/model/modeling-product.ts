export class ModelingProduct {

  constructor(id: string,
              name: string,
              category: string) {
    this.id = id;
    this.name = name;
    this.category = category;
  }

  id!: string;
  name!: string;
  category!: string;

}
