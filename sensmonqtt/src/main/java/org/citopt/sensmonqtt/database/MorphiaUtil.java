/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.citopt.sensmonqtt.database;

import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author rafaelkperes
 */
public class MorphiaUtil {

    private static MongoClient mongo = null;
    private static Datastore ds = null;

    public static MongoClient getMongoClient() throws UnknownHostException {
        if (mongo == null) {
            mongo = new MongoClient();
        }
        return mongo;
    }

    public static Datastore getDatastore(MongoClient mongoClient) {
        if (ds == null) {
            final Morphia morphia = new Morphia();
            final Datastore datastore = morphia.createDatastore(mongoClient, "sensor");

            morphia.mapPackage("org.citopt.sensmonqtt.device");
            morphia.mapPackage("org.citopt.sensmonqtt.user");

            datastore.ensureIndexes();

            ds = datastore;
        }
        return ds;
    }

    public static Datastore getDatastore(MongoClient mongoClient, String name) {
        final Morphia morphia = new Morphia();
        final Datastore datastore = morphia.createDatastore(mongoClient, name);

        morphia.mapPackage("org.citopt.sensmonqtt.device");
        morphia.mapPackage("org.citopt.sensmonqtt.user");

        datastore.ensureIndexes();

        return datastore;
    }

}