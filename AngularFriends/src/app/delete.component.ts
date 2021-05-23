import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {ScoreService} from './services/score_service.component';
import {timer} from 'rxjs';
import {Score} from './score';

@Component({
  selector: 'app-delete-score',
  templateUrl: 'delete.component.html'
})
export class DeleteComponent {
  scores = Score[];
  title = 'Delete Score';
  score: Score = {
    text: '',
    score: 0
  }

  constructor(private scoreservice: ScoreService, private router: Router) {
  }


  getScores(): void {
    // polling
    timer(0, 2500)
      .subscribe(() => {
        this.scoreservice.getAll()
          .subscribe(data => this.scores = data);
      });
  }

  ngOnInit(): void {
    this.getScores();
  }

  removeScore(): void {
    const score = {
      name: this.score.text,
      score: this.score.score,

    };

    this.scoreservice.create(score).subscribe(ok => {
      this.router.navigate(['/scores/delete']);
    }, error => {
      console.log(error);
    });
  }
}
