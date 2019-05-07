import {Component, OnInit} from '@angular/core';
import {TaskService} from '../../service/task.service';
import {TokenProvider} from '../../provider/token.provider';
import {Task} from '../../dto/Task';

@Component({
    selector: 'app-all-tasks',
    templateUrl: './all-tasks.component.html',
    styleUrls: ['./all-tasks.component.scss']
})
export class AllTasksComponent implements OnInit {
    tasks: Task[];

    constructor(
        private taskService: TaskService,
        private tokenProvider: TokenProvider
    ) {

    }

    ngOnInit() {
        this.tokenProvider.token.subscribe(t => {
            this.taskService.getAll(t).subscribe(ts => {
                console.log(ts);
                this.tasks = ts;
            });
        });
    }

}
