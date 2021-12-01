import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class DataService {
  constructor(private http: HttpClient) {}

  postMethodWithHeader(url: string, body: any) {
    const headers = new HttpHeaders();
    headers.set('content-type', 'application/json');
    return this.http.post(`${environment.apiUrl}/${url}`, body, {
      headers,
    });
  }

  isResponseOkAndNonNull(response: any): boolean {
    return (
      response.code != undefined &&
      response.code != null &&
      response.code === 200 &&
      response.response != undefined &&
      response.response != null &&
      Object.keys(response.response).length > 0
    );
  }

  isResponseOk(response: any): boolean {
    return (
      response.code != undefined &&
      response.code != null &&
      response.code === 200
    );
  }
}
