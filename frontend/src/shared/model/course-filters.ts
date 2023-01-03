export class CourseFilters {

  constructor(courseTitle: string,
              gameId: string,
              factionId: string,
              miniatureId: string,
              paintId: string,
              username: string,
              miniatureProducerId: string,
              modelingProductId: string) {
    this.courseTitle = courseTitle;
    this.gameId = gameId;
    this.factionId = factionId;
    this.miniatureId = miniatureId;
    this.paintId = paintId;
    this.username = username;
    this.miniatureProducerId = miniatureProducerId;
    this.modelingProductId = modelingProductId;
  }

  courseTitle!: string;
  gameId!: string;
  factionId!: string;
  miniatureId!: string;
  paintId!: string;
  username!: string;
  miniatureProducerId!: string;
  modelingProductId!: string;

}
