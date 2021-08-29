import "@vaadin/vaadin-text-field";
import "@vaadin/vaadin-button";
import "@vaadin/vaadin-grid";
import "@vaadin/vaadin-grid/src/vaadin-grid-column";
import { html } from 'lit';
import { customElement } from 'lit/decorators.js';
import { View } from '../view';
import '@vaadin/vaadin-text-field';
import "@vaadin/vaadin-charts";
import "@vaadin/vaadin-charts/src/vaadin-chart-series";
import { dashboardViewStore } from "./dashboard-view-store";
import { uiStore } from "Frontend/stores/app-store";


@customElement('dashboard-view')
export class DashboardView extends View {
connectedCallback() {
    super.connectedCallback();
    this.classList.add("flex", "flex-col", "items-center", "pt-xl");
  }

  render() {
    return html`
      <div class="text-xl mb-xl">
        ${dashboardViewStore.studentCount} students
      </div>
      ${this.getAuditoriumStats()}
    `;
  }

  getAuditoriumStats() {
    if (dashboardViewStore.auditoriumStats.length === 0) {
      if (uiStore.offline) {
        return html`<p>Connect to the internet to view stats</p>`;
      } else {
        return html`<p>Loading stats...</p>`;
      }
    } else {
      return html`
        <vaadin-chart type="pie">
          <vaadin-chart-series
            .values=${dashboardViewStore.auditoriumStats}
          ></vaadin-chart-series>
        </vaadin-chart>
      `;
    }
  }
}