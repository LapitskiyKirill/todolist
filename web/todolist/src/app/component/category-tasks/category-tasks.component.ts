import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {TokenProvider} from '../../provider/token.provider';
import {TaskService} from '../../service/task.service';
import {Task} from '../../dto/Task';
import {AppComponent} from '../../app.component';

@Component({
    selector: 'app-category-tasks',
    templateUrl: './category-tasks.component.html',
    styleUrls: ['./category-tasks.component.scss']
})
export class CategoryTasksComponent implements OnInit {
    tasks: Task[];
    @Input('currentCategoryNameOne')
    currentCategoryName: string;

    constructor(private app: AppComponent,
                private activatedRoute: ActivatedRoute,
                private taskService: TaskService,
                private tokenProvider: TokenProvider
    ) {

    }

    ngOnInit() {
        this.app.onLoad(() => {
            this.activatedRoute.params.subscribe(params => this.currentCategoryName = params['categoryName']);
            this.tokenProvider.token.subscribe(t => {
                this.taskService.getByCategory(t, this.currentCategoryName).subscribe(ts => {
                    this.tasks = ts;
                });
            });
            this.activatedRoute.params.subscribe(params => {
                this.currentCategoryName = params['categoryName'];
            });
        });
    }
}
