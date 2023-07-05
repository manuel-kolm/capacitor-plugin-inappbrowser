export interface BrowserOptions {
  url: string;
}

export interface InAppBrowserPlugin {
  open(options: BrowserOptions): Promise<void>;
  close(): Promise<void>
}
