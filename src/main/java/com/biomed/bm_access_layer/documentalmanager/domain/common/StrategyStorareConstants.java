package com.biomed.bm_access_layer.documentalmanager.domain.common;

/**
 * The {@code StrategyStorageConstants} class holds constant names for different storage strategies.
 * It provides easy access to the names used in storage strategies for the application.
 */
public class StrategyStorareConstants {

    /**
     * Constant representing the name of the GCP bucket storage strategy.
     */
    public static final String GCP_BUCKET_NAME = "gcp_bucket_name";
    /**
     * Constant representing the name of the local storage strategy.
     */
    public static final String LOCAL = "LOCAL";
    /**
     * Constant representing the name of the GCP cloud storage strategy.
     */
    public static final String GCP_CLOUD = "GCP_CLOUD";

    private StrategyStorareConstants() {
    }
}