import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ScoreService {
  private baseUrl = 'http://localhost:8080/ScoreServlet?command=';
  private scoresUrl = this.baseUrl + 'GetScore';
  private addUrl = this.baseUrl + 'AddScore';
  private deleteUrl = this.baseUrl + 'DeleteScore';

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(this.scoresUrl);
  }

  create(data): Observable<any> {
    const score = JSON.stringify(data);
    return this.http.post(this.addUrl, score);
  }

  delete(name): Observable<any> {
    return this.http.get(`${this.deleteUrl}&text=${name}`);
  }


  update(name, data): Observable<any> {
    return this.http.put(`${this.baseUrl}/${name}`, data);
  }

  findByScore(score): Observable<any> {
    return this.http.get(`${this.baseUrl}?title=${score}`);
  }
}
