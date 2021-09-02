import { Router } from '@vaadin/router';
import { routes } from './routes';
import { appStore } from './stores/app-store';
import '@vaadin/vaadin-button';
import '@vaadin/vaadin-grid';
import '@vaadin/vaadin-text-field';

window.addEventListener('load', () => {
  initUI();
});

export const router = new Router(document.querySelector('#outlet'));

router.setRoutes(routes);

window.addEventListener('vaadin-router-location-changed', (e) => {
  appStore.setLocation((e as CustomEvent).detail.location);
  const title = appStore.currentViewTitle;
  if (title) {
    document.title = title + ' | ' + appStore.applicationName;
  } else {
    document.title = appStore.applicationName;
  }
});

function initUI() {
  const firstNameField = document.querySelector('#firstName');
  const lastNameField = document.querySelector('#lastName');
  const addButton = document.querySelector('#addButton');
  const grid = document.querySelector('#grid');
  let people = [];

  addButton.addEventListener('click', e => {
    people = [
      ...people,
      {
        firstName: firstNameField.value,
        lastName: lastNameField.value
      }
    ];
    grid.items = people;
    firstNameField.value = '';
    lastNameField.value = '';
  });
}
