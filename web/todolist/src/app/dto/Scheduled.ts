import {Periodicity} from './Periodicity';

export class Scheduled {
    id: number;
    taskId: number;
    from: Date;
    periodicity: Periodicity;
    deleted: boolean;
}
