import {Component, OnInit} from '@angular/core';
import {AppComponent} from '../../app.component';

@Component({
    selector: 'app-tasks',
    templateUrl: './tasks.component.html',
    styleUrls: ['./tasks.component.scss']
})
export class TasksComponent implements OnInit {

    constructor(private app: AppComponent) {

    }

    ngOnInit() {

    }
}
