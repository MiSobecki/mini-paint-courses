export class CourseMiniature {

  constructor(id: string,
              name: string,
              type: string,
              factionName: string,
              gameTitle: string) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.factionName = factionName;
    this.gameTitle = gameTitle;
  }

  id!: string;
  name!: string;
  type!: string;
  factionName!: string;
  gameTitle!: string;

}
