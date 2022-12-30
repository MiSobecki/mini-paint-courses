export class CourseStep {

  constructor(id: string | null,
              orderNumber: number,
              title: string,
              description: string,
              paintTechniqueIdToPaintIdMap: { [name: string]: string },
              usedOtherModelingProductIds: string[]) {
    if (id) {
      this.id = id;
    }
    this.orderNumber = orderNumber;
    this.title = title;
    this.description = description;
    this.usedOtherModelingProductIds = usedOtherModelingProductIds;

    this.paintTechniqueIdToPaintIdMap = new Map;

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
