import { approvalStore } from 'Frontend/stores/app-store';
import { makeAutoObservable } from 'mobx';

class DashboardViewStore {
  constructor() {
    makeAutoObservable(this);
  }

  get studentCount() {
    return approvalStore.students.length;
  }

  get auditoriumStats() {
    const countByAuditorium = approvalStore.students.reduce((map, student) => {
      const name = student.auditorium?.name || 'Unknown';
      return map.set(name, (map.get(name) || 0) + 1);
    }, new Map<string, number>());

    return Array.from(countByAuditorium.entries()).map(([auditorium, participants]) => ({
      name: auditorium,
      y: participants,
    }));
  }
}

export const dashboardViewStore = new DashboardViewStore();