import {Component, OnInit} from '@angular/core';
import {CategoryService} from '../../service/category.service';
import {Category} from '../../dto/Category';
import {TaskService} from '../../service/task.service';
import {TokenProvider} from '../../provider/token.provider';

@Component({
    selector: 'app-tasks',
    templateUrl: './tasks.component.html',
    styleUrls: ['./tasks.component.scss']
})
export class TasksComponent implements OnInit {

    constructor() {

    }

    ngOnInit() {
    }
}
