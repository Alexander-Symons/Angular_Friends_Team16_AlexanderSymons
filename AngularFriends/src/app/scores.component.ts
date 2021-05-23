import { Component, OnInit } from '@angular/core';
import {timer} from 'rxjs';
import {Score} from './score';
import {ScoreService} from './services/score_service.component';

@Component({
  selector: 'app-score',
  templateUrl: './scores.component.html'
})
export class ScoresComponent implements OnInit {
  scores: Score[];

  constructor(private scoreService: ScoreService) { }

  getScores(): void {
    // polling
    timer(0, 2000)
      .subscribe(() => {
        this.scoreService.getAll()
          .subscribe(data => this.scores = data);
      });
  }

  ngOnInit(): void {
    this.getScores();
  }
}
