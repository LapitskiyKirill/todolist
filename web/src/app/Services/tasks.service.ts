import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Task} from '../Entities/Task';
import {HttpClient} from '@angular/common/http';
import {Category} from '../Entities/Category';
import {SERVER_PATH} from '../../globals';

@Injectable({
  providedIn: 'root'
})
export class TasksService {

  constructor(private http: HttpClient) {
  }

  getAllTasks(token: string): Observable<Task[]> {
    return this.http.get<Task[]>(SERVER_PATH + '/task/getAll', {
      headers: {
        token
      }
    });
  }
}
