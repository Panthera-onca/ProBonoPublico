import { makeAutoObservable, observable, runInAction } from 'mobx';

import Auditorium from 'Frontend/generated/com/example/application/data/entity/Auditorium';
import Student from 'Frontend/generated/com/example/application/data/entity/Student';
import Status from 'Frontend/generated/com/example/application/data/entity/Status';
import * as endpoint from 'Frontend/generated/CrmEndpoint';
import ApprovalModel from 'Frontend/generated/com/example/application/data/endpoint/ApprovalEndpoint/ApprovalModel';
import { cacheable } from './cacheable';
import { uiStore } from './app-store';

export class ApprovalStore {
  students: Student[] = [];
  auditoriums: Auditorium[] = [];
  statuses: Status[] = [];

  constructor() {
    makeAutoObservable(
      this,
      {
        initFromServer: false,
        students: observable.shallow,
        auditoriums: observable.shallow,
        statuses: observable.shallow,
      },
      { autoBind: true }
    );

    this.initFromServer();
  }

  async initFromServer() {
    const data = await cacheable(
      endpoint.getApproval,
      'approval',
      ApprovalModel.createEmptyValue()
    );

    runInAction(() => {
      this.students = data.students;
      this.auditoriums = data.auditoriums;
      this.statuses = data.statuses;
    });
  }

  async saveStudent(student: Student) {
    try {
      const saved = await endpoint.saveStudent(student);
      if (saved) {
        this.saveLocal(saved);
        uiStore.showSuccess('Student saved.');
      } else {
        uiStore.showError('Student save failed.');
      }
    } catch (e) {
      console.log(e);
      uiStore.showError('Student save failed.');
    }
  }

  async deleteStudent(student: Student) {
    if (!student.id) return;

    try {
      await endpoint.deleteContact(student.id);
      this.deleteLocal(student);
      uiStore.showSuccess('Student deleted.');
    } catch (e) {
      console.log(e);
      uiStore.showError('Failed to delete student.');
    }
  }

  private saveLocal(saved: Student) {
    const studentExists = this.students.some((s) => s.id === saved.id);
    if (studentExists) {
      this.students = this.students.map((existing) => {
        if (existing.id === saved.id) {
          return saved;
        } else {
          return existing;
        }
      });
    } else {
      this.students.push(saved);
    }
  }

  private deleteLocal(students: Student) {
    this.students = this.students.filter((s) => s.id !== student.id);
  }
}