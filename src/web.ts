import { WebPlugin } from '@capacitor/core';

import type { BrowserOptions, InAppBrowserPlugin } from './definitions';

export class InAppBrowserWeb extends WebPlugin implements InAppBrowserPlugin {
  async open(_: BrowserOptions): Promise<void> {
    throw new Error('Method not implemented.');
  }
  async close(): Promise<void> {
    throw new Error('Method not implemented.');
  }
}
