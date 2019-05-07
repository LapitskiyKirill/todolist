import {Component, OnInit} from '@angular/core';
import {CategoryService} from '../../service/category.service';
import {Category} from '../../dto/Category';
import {Task} from '../../dto/Task';
import {TaskService} from '../../service/task.service';
import {TokenProvider} from '../../provider/token.provider';

@Component({
    selector: 'app-tasks',
    templateUrl: './tasks.component.html',
    styleUrls: ['./tasks.component.scss']
})
export class TasksComponent implements OnInit {
    categories: Category[];
    tasks: Task[];

    constructor(
        private categoryService: CategoryService,
        private taskService: TaskService,
        private tokenProvider: TokenProvider
    ) {

    }

    ngOnInit() {
        this.tokenProvider.token.subscribe(t => {
            this.categoryService.getCategories(t).subscribe(cs => {
                this.categories = cs;
            });

        });
    }
}
