import { PluginListenerHandle } from "@capacitor/core";

export interface OpenOptions {
  url: string;
}

export interface CloseOptions {
  id: string;
}

export interface InAppBrowserPlugin {
  open(options: OpenOptions): Promise<void>;
  close(options: CloseOptions): Promise<void>
  
  addListener(
    eventName: 'open',
    listenerFunc: () => void,
  ): Promise<PluginListenerHandle> & PluginListenerHandle;

  addListener(
    eventName: 'close',
    listenerFunc: () => void,
  ): Promise<PluginListenerHandle> & PluginListenerHandle;
}
