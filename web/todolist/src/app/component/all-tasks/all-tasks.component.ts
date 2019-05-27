import {Component, OnInit} from '@angular/core';
import {TaskService} from '../../service/task.service';
import {TokenProvider} from '../../provider/token.provider';
import {Task} from '../../dto/Task';
import {AppComponent} from '../../app.component';

@Component({
    selector: 'app-all-tasks',
    templateUrl: './all-tasks.component.html',
    styleUrls: ['./all-tasks.component.scss']
})
export class AllTasksComponent implements OnInit {
    tasks: Task[];

    constructor(
        private app: AppComponent,
        private taskService: TaskService,
        private tokenProvider: TokenProvider
    ) {

    }

    ngOnInit() {
        this.app.onLoad(() => {
            this.tokenProvider.token.subscribe(t => {
                this.taskService.getAll(t).subscribe(ts => {
                    this.tasks = ts;
                });
            });
        });

    }
}
