import { crmStore } from 'Frontend/stores/app-store';
import { makeAutoObservable } from 'mobx';

class DashboardViewStore {
  constructor() {
    makeAutoObservable(this);
  }

  get studentCount() {
    return crmStore.students.length;
  }

  get auditoriumStats() {
    const countByAuditorium = crmStore.students.reduce((map, student) => {
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