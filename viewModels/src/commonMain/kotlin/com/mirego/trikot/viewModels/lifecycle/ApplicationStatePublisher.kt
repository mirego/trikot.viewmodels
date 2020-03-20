package com.mirego.trikot.viewModels.lifecycle

import org.reactivestreams.Publisher

expect class ApplicationStatePublisher() : Publisher<ApplicationState>
