package marauroa.common.game.test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import marauroa.common.game.Definition;
import marauroa.common.game.RPClass;
import marauroa.common.game.RPEvent;
import marauroa.common.game.RPObject;
import marauroa.common.game.Definition.DefinitionClass;
import marauroa.common.game.Definition.Type;
import marauroa.common.net.InputSerializer;
import marauroa.common.net.OutputSerializer;

import org.junit.Test;

/**
 * Test unit for RPEvent class
 * @author miguel
 *
 */
public class TestRPEvent {
	/**
	 * Test serialization of a RPEvent by serializing into a stream and deserializing it bak
	 * again.
	 * This test uses RPObject as they are needed to obtain the RPEvent code definition.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@Test
	public void testSerialization() throws IOException, ClassNotFoundException {
		RPObject obj=new RPObject();

		RPEvent expected=new RPEvent(obj, "test", "work!");

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		OutputSerializer os = new OutputSerializer(out);

		os.write(expected);

		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		InputSerializer is = new InputSerializer(in);

		RPEvent result=(RPEvent) is.readObject(new RPEvent(obj));

		assertEquals(expected, result);
	}

	/**
	 * Test serialization of a RPEvent by serializing into a stream and deserializing it bak
	 * again.
	 * This test uses RPObject as they are needed to obtain the RPEvent code definition.
	 * This test also uses RPClass definition
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@Test
	public void testSerializationWithRPClass() throws IOException, ClassNotFoundException {
		RPClass clazz=new RPClass("A");

		clazz.add(DefinitionClass.RPEVENT, "test", Type.STRING, Definition.STANDARD);

		RPObject obj=new RPObject();
		obj.setRPClass(clazz);

		RPEvent expected=new RPEvent(obj, "test", "work!");

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		OutputSerializer os = new OutputSerializer(out);

		os.write(expected);

		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		InputSerializer is = new InputSerializer(in);

		RPEvent result=(RPEvent) is.readObject(new RPEvent(obj));

		assertEquals(expected, result);
	}}