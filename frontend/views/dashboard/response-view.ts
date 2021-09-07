import { View } from '../view';
import "@vaadin/vaadin-charts/src/vaadin-chart-series";
import { customElement, html, state } from "lit-element";
import "@vaadin/vaadin-charts";


@customElement("client-side-view-displaying-live-data")
export class ResponseView extends View {

  @state()
  private ticker = "APPROVAL";
  @state()
  private currentApproval = "";
  @state()
  private approvalHistory: number[][] = [];

 render() {
    return html`
      <h2>${this.ticker} - $${this.currentApproval}</h2>
      <p>
        The backend in this demo sends 30 data points.
        <a href="/show-real-time-updating-data"
          >See this example using the server-side Java API.</a
        >
      </p>

      <vaadin-chart>
        <vaadin-chart-series
          title=${this.ticker}
          .values=${this.approvalHistory}
        ></vaadin-chart-series>
      </vaadin-chart>
    `;
  }

  connectedCallback() {
    super.connectedCallback();

    // Connect to the backend
    const approvalSource = new EventSource(
      `${window.location.origin}/approval`
    );

    // Listen for incoming data and update state
    approvalSource.addEventListener("message", (e) => {
      this.currentApproval = JSON.parse(e.data);

      // The price history array contains pairs of [x,y] values
      this.approvalHistory = [
        ...this.approvalHistory,
        [this.approvalHistory.length, parseFloat(this.currentApproval)],
      ];
    });

    // The backend only sends 30 points, close after that
    approvalSource.addEventListener("error", () => approvalSource.close());
  }
}