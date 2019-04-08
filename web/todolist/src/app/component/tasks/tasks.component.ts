import {Component, Input, OnInit} from '@angular/core';
import {CategoryService} from '../../service/category.service';
import {Category} from '../../dto/Category';
import {map} from 'rxjs/operators';

@Component({
    selector: 'app-tasks',
    templateUrl: './tasks.component.html',
    styleUrls: ['./tasks.component.scss']
})
export class TasksComponent implements OnInit {
    categories: Array<Category>;

    constructor(private categoryService: CategoryService) {

    }

    ngOnInit() {
        this.categoryService.getCategories().subscribe(c => this.categories = c);
    }
}
