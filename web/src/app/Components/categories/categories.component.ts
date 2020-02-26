import {Component, OnInit} from '@angular/core';
import {CategoriesService} from '../../Services/categories.service';
import {Category} from '../../Entities/Category';
import {faPlus} from '@fortawesome/free-solid-svg-icons/faPlus';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss']
})
export class CategoriesComponent implements OnInit {
  categories: Category[];
  plusIcon = faPlus;

  constructor(private categoryService: CategoriesService) {
    this.categoryService.getCategories(localStorage.getItem('token')).subscribe(cs => {
      this.categories = cs;
    });
  }

  ngOnInit() {
  }

}
