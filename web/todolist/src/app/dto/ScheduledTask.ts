import {Periodicity} from './Periodicity';

export class ScheduledTask {
    constructor(id:number, text: string, from: Date, periodicity: Periodicity) {
        this.id = id;
        this.text = text;
        this.from = from;
        this.periodicity = periodicity;
    }

    id: number;
    text: string;
    from: Date;
    periodicity: Periodicity;
}
