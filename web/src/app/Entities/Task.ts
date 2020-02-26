import {Category} from './Category';

export class Task {
  id: number;
  category: Category;
  text: string;
  deadline: Date;
  completed: Date;
  subtasks: Task[];
}
