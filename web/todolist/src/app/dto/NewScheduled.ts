import {Periodicity} from './Periodicity';

export class NewScheduled {
    public taskId: number;
    public from: string;
    public periodicity: string;

    constructor(taskId: number, from: string, periodicity: string) {
        this.taskId = taskId;
        this.from = from;
        this.periodicity = periodicity;
    }
}
