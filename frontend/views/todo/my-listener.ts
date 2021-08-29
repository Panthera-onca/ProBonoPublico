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

@customElement('my-listener')
class MyListener extends LitElement{
	@property() canCheck = false;
	protected render() {
    return html`
      <p @checked=${this._checkedHandler}><slot></slot></p>
      <hr>
      <p>${this.canCheck ? 'Clarification needed' : 'Clarification necessary'} check</p>
      <p><button @click=${this._clickHandler}>Unclear</button></p>`;
  }

  private _checkedHandler(e: CustomEvent) {
    if (!this.canCheck) {
      e.preventDefault();
      e.detail.message = '✅ Click unclear before checking!';
    }
  }
  private _clickHandler() {
    this.canCheck = !this.canCheck;
  }

}