import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {ScoreService} from './services/score_service.component';
import {timer} from 'rxjs';
import {Score} from './score';

@Component({
  selector: 'app-create-score',
  templateUrl: 'create.component.html'
})
export class CreateComponent {
  scores = Score[];
  title = 'Change Score';
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

  addScore(): void {
    const score = {
      name: this.score.text,
      score: this.score.score,

    };

    this.scoreservice.create(score).subscribe(ok => {
      this.router.navigate(['/scores/add']);
    }, error => {
      console.log(error);
    });
  }
}
