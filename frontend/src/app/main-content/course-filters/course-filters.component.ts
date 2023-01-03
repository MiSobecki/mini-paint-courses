import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {GameService} from "../../../shared/service/game.service";
import {Observable, of} from "rxjs";
import {GameShortInfo} from "../../../shared/model/game-short-info";
import {FactionService} from "../../../shared/service/faction.service";
import {FactionShortInfo} from "../../../shared/model/faction-short-info";
import {MiniatureShortInfo} from "../../../shared/model/miniature-short-info";
import {MiniatureService} from "../../../shared/service/miniature.service";
import {Paint} from "../../../shared/model/paint";
import {PaintService} from "../../../shared/service/paint.service";
import {Producer} from "../../../shared/model/producer";
import {ProducerService} from "../../../shared/service/producer.service";
import {ModelingProduct} from "../../../shared/model/modeling-product";
import {ModelingProductService} from "../../../shared/service/modeling-product.service";
import {CourseFilters} from "../../../shared/model/course-filters";

@Component({
  selector: 'app-course-filters',
  templateUrl: './course-filters.component.html',
  styleUrls: ['./course-filters.component.scss']
})
export class CourseFiltersComponent implements OnInit {

  filtersForm: FormGroup;

  games$: Observable<GameShortInfo[]> = of([]);
  factions$: Observable<FactionShortInfo[]> = of([]);
  miniatures$: Observable<MiniatureShortInfo[]> = of([]);
  paints$: Observable<Paint[]> = of([]);
  producers$: Observable<Producer[]> = of([]);
  modelingProducts$: Observable<ModelingProduct[]> = of([]);

  @Output() search = new EventEmitter<CourseFilters>();

  constructor(private formBuilder: FormBuilder,
              private gameService: GameService,
              private factionService: FactionService,
              private miniatureService: MiniatureService,
              private paintService: PaintService,
              private producerService: ProducerService,
              private modelingProductService: ModelingProductService) {
    this.filtersForm = this.formBuilder.group({});
  }

  ngOnInit(): void {
    this.filtersForm = this.formBuilder.group({
      courseTitle: [''],
      gameId: [''],
      factionId: [{value: '', disabled: true}],
      miniatureId: [{value: '', disabled: true}],
      paintId: [''],
      courseAuthor: [''],
      producerId: [''],
      modelingProductId: ['']
    });

    this.gameService.findAll();
    this.games$ = this.gameService.gamesShortInfo$;

    this.paintService.findAll();
    this.paints$ = this.paintService.paints$;

    this.producerService.findAll();
    this.producers$ = this.producerService.producers$;

    this.modelingProductService.findAll();
    this.modelingProducts$ = this.modelingProductService.modelingProducts$;
  }

  get gameId(): string {
    return this.filtersForm.get('gameId')?.value;
  }

  get factionIdControl(): FormControl {
    return this.filtersForm.get('factionId') as FormControl;
  }

  onGameSelect(): void {
    this.factionService.findAllByGame(this.gameId);
    this.factions$ = this.factionService.factionsShortInfo$;

    this.factionIdControl.enable();
  }

  get miniatureIdControl(): FormControl {
    return this.filtersForm.get('miniatureId') as FormControl;
  }

  onFactionSelect(): void {
    this.miniatureService.findAllByFaction(this.factionIdControl.value);
    this.miniatures$ = this.miniatureService.miniaturesShortInfo$;

    this.miniatureIdControl.enable();
  }

  onResetFilters(): void {
    this.filtersForm = this.formBuilder.group({
      courseTitle: [''],
      gameId: [''],
      factionId: [{value: '', disabled: true}],
      miniatureId: [{value: '', disabled: true}],
      paintId: [''],
      courseAuthor: [''],
      producerId: [''],
      modelingProductId: ['']
    });

    this.search.emit(this.courseFilters);
  }

  onSearch(): void {
    this.search.emit(this.courseFilters);
  }

  get courseFilters(): CourseFilters {
    return new CourseFilters(
      this.filtersForm.get('courseTitle')?.value,
      this.filtersForm.get('gameId')?.value,
      this.filtersForm.get('factionId')?.value,
      this.filtersForm.get('miniatureId')?.value,
      this.filtersForm.get('paintId')?.value,
      this.filtersForm.get('username')?.value,
      this.filtersForm.get('producerId')?.value,
      this.filtersForm.get('modelingProductId')?.value);
  }

}
