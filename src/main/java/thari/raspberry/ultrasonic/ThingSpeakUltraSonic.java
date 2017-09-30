package thari.raspberry.ultrasonic;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.util.Console;

/**
 * Created by Tharinda on 30-Sep-17.
 */
public class ThingSpeakUltraSonic
{
    public static void main( String[] args )
    {
        System.out.println( "Hello Thari Raspberry" );
        try
        {
            ThingSpeakUltraSonic thingSpeakUltraSonic = new ThingSpeakUltraSonic();
            thingSpeakUltraSonic.start();
        }
        catch( InterruptedException e )
        {
            e.printStackTrace();
        }
    }


    private void start() throws InterruptedException
    {
        Console console = new Console();
        console.title( "<-- The Pi4J Project -->", "ThingSpeak UltraSonic" );
        console.promptForExit();
        GpioController gpio = GpioFactory.getInstance();


        while( true )
        {
            final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin( RaspiPin.GPIO_21, "MyLED", PinState.HIGH );
            pin.setShutdownOptions( true, PinState.LOW );


            System.out.println( "--> GPIO state should be: ON" );

            Thread.sleep( 1000 );

            // turn off gpio pin #01
            pin.low();
            System.out.println( "--> GPIO state should be: OFF" );

            Thread.sleep( 1000 );

            // toggle the current state of gpio pin #01 (should turn on)
            pin.toggle();
            System.out.println( "--> GPIO state should be: ON" );

            Thread.sleep( 1000 );

            // toggle the current state of gpio pin #01  (should turn off)
            pin.toggle();
            System.out.println( "--> GPIO state should be: OFF" );

            Thread.sleep( 1000 );

            // turn on gpio pin #01 for 1 second and then off
            System.out.println( "--> GPIO state should be: ON for only 1 second" );
            pin.pulse( 1000, true ); // set second argument to 'true' use a blocking call

            // stop all GPIO activity/threads by shutting down the GPIO controller
            // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
            gpio.shutdown();

            System.out.println( "Exiting ControlGpioExample" );
        }


    }
}
