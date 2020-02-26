import {Component, OnInit} from '@angular/core';
import {faCheck, faClipboardCheck, faLink, faPlus, faTasks, faTimes} from '@fortawesome/free-solid-svg-icons';
import {Task} from 'src/app/Entities/Task';
import {TasksService} from '../../Services/tasks.service';

@Component({
  selector: 'app-all-tasks',
  templateUrl: './all-tasks.component.html',
  styleUrls: ['./all-tasks.component.scss']
})
export class AllTasksComponent implements OnInit {

  tasks: Task[];
  plusIcon = faPlus;
  linkIcon = faLink;
  checkIcon = faCheck;
  clipCheckIcon = faClipboardCheck;
  tasksIcon = faTasks;
  timesIcon = faTimes;


  constructor(
    private taskService: TasksService
  ) {
    this.taskService.getAllTasks(localStorage.getItem('token')).subscribe(ts => {
      this.tasks = ts;
      console.log(this.tasks);
    });
  }

  ngOnInit() {
  }

}
