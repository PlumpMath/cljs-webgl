(ns cljswebgl.gl)
  
(defn get-context
  
  ([canvas-element]
     (.getContext canvas-element "experimental-webgl"))
  
  ([canvas-element context-attributes]
     (let [default-attributes {:alpha true
                               :depth true,
                               :stencil false,
                               :antialias true,
                               :premultiplied-alpha true,
                               :preserve-drawing-buffer false}
           attributes->js (fn
                            [{alpha :alpha depth :depth stencil :stencil antialias :antialias
                              premultiplied-alpha :premultiplied-alpha preserve-drawing-buffer :preserve-drawing-buffer}]
                            (clj->js {:alpha alpha,
                                      :depth depth,
                                      :stencil stencil,
                                      :antialias antialias,
                                      :premultipliedAplha premultiplied-alpha,
                                      :preserveDrawingBuffer preserve-drawing-buffer}))]
       (.getContext canvas-element "experimental-webgl" (attributes->js (merge default-attributes context-attributes))))))

(defn get-context-attributes
  [gl-context]
  (let [js-obj (.getContextAttributes gl-context)]
    {:alpha (.-alpha js-obj),
     :depth (.-depth js-obj),
     :stencil (.-stencil js-obj),
     :antialias (.-antialias js-obj),
     :premultiplied-alpha (.-premultipliedAlpha js-obj),
     :preserve-drawing-buffer (.-preserveDrawingBuffer js-obj)
     }))

(defn get-canvas
  [gl-context]
  (.-canvas gl-context))

(defn get-drawing-buffer-width
  [gl-context]
  (.-drawingBufferWidth gl-context))

(defn get-drawing-buffer-height
  [gl-context]
  (.-drawingBufferHeight gl-context))

(defn is-context-lost?
  [gl-context]
  (.isContextLost gl-context))

(defn get-supported-extensions
  [gl-context]
  (lazy-seq (.getSupportedExtensions gl-context)))

; TODO: We need to wrap the extension object in clojure constructs in some way.
(defn get-extension
  [gl-context extension-name]
  (.getExtension gl-context extension-name))
