export class CourseStep {

  constructor(id: string,
              orderNumber: number,
              title: string,
              description: string,
              paintTechniqueIdToPaintIdMap: { [name: string]: string },
              usedOtherModelingProductIds: string[]) {
    this.id = id;
    this.orderNumber = orderNumber;
    this.title = title;
    this.description = description;
    this.usedOtherModelingProductIds = usedOtherModelingProductIds;

    Object.keys(paintTechniqueIdToPaintIdMap).forEach(key => {
      this.paintTechniqueIdToPaintIdMap.set(key, paintTechniqueIdToPaintIdMap[key]);
    });
  }

  id!: string;
  orderNumber!: number;
  title!: string;
  description!: string;
  paintTechniqueIdToPaintIdMap!: Map<string, string>;
  usedOtherModelingProductIds!: string[];

}
