package views;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.event.SelectListener;
import gov.nasa.worldwind.examples.Airspaces.AirspacesController;
import gov.nasa.worldwind.examples.ApplicationTemplate;
import gov.nasa.worldwind.examples.LayerPanel;
import gov.nasa.worldwind.geom.*;
import gov.nasa.worldwind.layers.AirspaceLayer;
import gov.nasa.worldwind.layers.AnnotationLayer;
import gov.nasa.worldwind.pick.PickedObjectList;
import gov.nasa.worldwind.render.*;
import gov.nasa.worldwind.render.airspaces.*;
import gov.nasa.worldwind.util.BasicDragger;
import gov.nasa.worldwind.view.orbit.BasicOrbitView;


public class HelloWorld extends ApplicationTemplate {

    public static final String ACTION_COMMAND_LOAD_DEMO_AIRSPACES = "ActionCommandLoadDemoAirspaces";
	public static final String DESCRIPTION = "Description";
	public static class HelloWorldFrame extends ApplicationTemplate.AppFrame
	{

		protected AirspacesController controller;
        protected LayerPanel layerPanel;

		public HelloWorldFrame()
		{
			// We add our own LayerPanel, but keep the StatusBar from ApplicationTemplate.
			super(true, false, false);
			this.controller = new AirspacesController(this.getWwd());
			this.controller.frame = this;
			this.makeComponents();

			//((FlatGlobe) this.getWwd().getModel().getGlobe()).setProjection(FlatGlobe.PROJECTION_MODIFIED_SINUSOIDAL);
			this.controller.actionPerformed(new ActionEvent(this, 0, ACTION_COMMAND_LOAD_DEMO_AIRSPACES));
			this.getLayerPanel().update(this.getWwd());

			this.pack();
		}
		


        public LayerPanel getLayerPanel()
        {
            return this.layerPanel;
        }


		protected void makeComponents()
		{
			this.getWwd().setPreferredSize(new Dimension(1024, 768));

            JPanel panel = new JPanel(new BorderLayout());
            {
            	this.layerPanel = new LayerPanel(this.getWwd(), null);
                panel.add(this.layerPanel, BorderLayout.CENTER);
            }
            getContentPane().add(panel, BorderLayout.WEST);
		}
	}

	public static class AirspacesController implements ActionListener
	{
		// AWT/Swing stuff.
		protected HelloWorldFrame frame;
		protected AirspaceLayer oAirspaces;
		// World Wind stuff.
		protected WorldWindowGLCanvas wwd;
		protected BasicDragger dragger;

		public AirspacesController(WorldWindowGLCanvas wwd)
		{
			this.wwd = wwd;
			oAirspaces = new AirspaceLayer();
			this.initializeSelectionMonitoring();
		}
		public void setAirspaces(Collection<Airspace> airspaces)
        {
            this.oAirspaces.removeAllAirspaces();

            if (airspaces != null)
            {
                for (Airspace a : airspaces)
                {
                    if (a == null)
                        continue;
                    this.oAirspaces.addAirspace(a);
                }
            }
        }
		public void actionPerformed(ActionEvent e)
		{

            this.doLoadDemoAirspaces();

		}

		public void initializeSelectionMonitoring()
		{
			this.dragger = new BasicDragger(this.wwd);
			this.wwd.addSelectListener(new SelectListener()
			{
				public void selected(SelectEvent event)
				{
					// Have rollover events highlight the rolled-over object.
					if (event.getEventAction().equals(SelectEvent.ROLLOVER) && !dragger.isDragging())
					{
							AirspacesController.this.wwd.redraw();
					}
					// Have hover events popup an annotation about the hovered-over object.
					else if (event.getEventAction().equals(SelectEvent.HOVER) && !dragger.isDragging())
					{
							AirspacesController.this.wwd.redraw();
					}
					// Have drag events drag the selected object.
					else if (event.getEventAction().equals(SelectEvent.DRAG_END)
							|| event.getEventAction().equals(SelectEvent.DRAG))
					{
						// Delegate dragging computations to a dragger.
						dragger.selected(event);

						// We missed any roll-over events while dragging, so highlight any under the cursor now,
						// or de-highlight the dragged shape if it's no longer under the cursor.
						if (event.getEventAction().equals(SelectEvent.DRAG_END))
						{
							PickedObjectList pol = AirspacesController.this.wwd.getObjectsAtCurrentPosition();
							if (pol != null)
							{
								AirspacesController.this.wwd.repaint();
							}
						}
					}
				}
			});
		}

		protected Annotation createToolTip(Airspace a, SelectEvent e)
		{
			Object o = a.getValue(DESCRIPTION);
			if (o == null)
				o = a.getClass().getName();

			java.awt.Point point = e.getPickPoint();

			Annotation annotation;

			Position pos = wwd.getView().computePositionFromScreenPoint(point.x, point.y);
			if (pos != null)
			{
				double[] altitudes = a.getAltitudes();
				pos = new Position(pos.getLatitude(), pos.getLongitude(), altitudes[1]);
				annotation = new GlobeAnnotation(o.toString(), pos);
			}
			else
			{
				annotation = new ScreenAnnotation(o.toString(), point);
			}

			annotation.setAlwaysOnTop(true);
			annotation.setPickEnabled(false);

			return annotation;
		}

		protected void setupDefaultMaterial(Airspace a, Color color)
		{
			Color outlineColor = makeBrighter(color);

			a.getAttributes().setDrawOutline(true);
			a.getAttributes().setMaterial(new Material(color));
			a.getAttributes().setOutlineMaterial(new Material(outlineColor));
			a.getAttributes().setOpacity(0.8);
			a.getAttributes().setOutlineOpacity(0.9);
			a.getAttributes().setOutlineWidth(3.0);
		}

		protected static Color makeBrighter(Color color)
		{
			float[] hsbComponents = new float[3];
			Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsbComponents);
			float hue = hsbComponents[0];
			float saturation = hsbComponents[1];
			float brightness = hsbComponents[2];

			saturation /= 3f;
			brightness *= 3f;

			if (saturation < 0f)
				saturation = 0f;

			if (brightness > 1f)
				brightness = 1f;

			int rgbInt = Color.HSBtoRGB(hue, saturation, brightness);

			return new Color(rgbInt);
		}

		public void doLoadDemoAirspaces()
		{
			ArrayList<Airspace> airspaces = new ArrayList<Airspace>();

			 // Route
            Route route = new Route();
            route.setAltitudes(5000.0, 20000.0);
            route.setWidth(20000.0);
            route.setLocations(Arrays.asList(
                LatLon.fromDegrees(43.0, -121.0),
                LatLon.fromDegrees(44.0, -121.0),
                LatLon.fromDegrees(44.0, -120.0),
                LatLon.fromDegrees(43.0, -120.0)));
            route.setTerrainConforming(false, false);
            this.setupDefaultMaterial(route, Color.BLUE);
            airspaces.add(route);
            setAirspaces(airspaces);

		}
	}




	public static void main(String[] args)
	{
		start("World Wind Airspaces", HelloWorldFrame.class);
	}
}
