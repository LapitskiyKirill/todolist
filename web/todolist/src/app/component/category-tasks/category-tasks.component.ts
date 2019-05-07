import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {TokenProvider} from '../../provider/token.provider';
import {TaskService} from '../../service/task.service';
import {Task} from '../../dto/Task';

@Component({
    selector: 'app-category-tasks',
    templateUrl: './category-tasks.component.html',
    styleUrls: ['./category-tasks.component.scss']
})
export class CategoryTasksComponent implements OnInit {
    tasks: Task[];
    category: string;

    constructor(private activatedRoute: ActivatedRoute,
                private taskService: TaskService,
                private tokenProvider: TokenProvider
    ) {

    }

    ngOnInit() {
        this.activatedRoute.params.subscribe(params => this.category = params.categoryName);
        this.tokenProvider.token.subscribe(t => {
            this.taskService.getByCategory(t, this.category).subscribe(ts => {
                console.log(this.category);
                console.log(t);
                this.tasks = ts;
            });
        });

    }

}
