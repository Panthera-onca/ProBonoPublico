import {
  LitElement,
  html,
  css,
  customElement,
  property,
} from "lit-element";

import "@vaadin/vaadin-text-field";
import "@vaadin/vaadin-button";
import "@vaadin/vaadin-checkbox";
import { Binder, field } from "@vaadin/form";


@customElement('my-dispatcher')
export class DataBindingView extends LitElement {
  @property() label = 'Clear';
  defaultMessage = '👍';
  @property() message =this.defaultMessage;
  private _resetMessage?: ReturnType<typeof setTimeout>;


  static styles = css`
    :host {
      display: block;
      padding: var(--lumo-space-m) var(--lumo-space-l);
    }
  `;

  protected render() {
    return html`
      <label><input type="checkbox" @click=${this._tryChange}>${this.label}</label>
      <div>${this.message}</div>
    `;
  }

  private _tryChange(e: Event) {
    const detail = {message: this.message};
    const event = new CustomEvent('checked', {detail, bubbles: true, composed: true, cancelable: true});
    this.dispatchEvent(event);
    if (event.defaultPrevented) {
      e.preventDefault();
    }
    this.message = detail.message;
  }
  protected updated() {
    this._resetMessage = setTimeout(() => this.message = this.defaultMessage, 1000);
    clearTimeout(this._resetMessage);
  }

  
}