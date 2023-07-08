import { WebPlugin } from '@capacitor/core';

import type { OpenOptions, InAppBrowserPlugin, CloseOptions } from './definitions';

export class InAppBrowserWeb extends WebPlugin implements InAppBrowserPlugin {
  async open(_: OpenOptions): Promise<void> {
    throw new Error('Method not implemented.');
  }
  async close(_: CloseOptions): Promise<void> {
    throw new Error('Method not implemented.');
  }
}
