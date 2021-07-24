INSERT INTO PUBLIC.mt_sub_btype
            (id,
             created_on,
             updated_on,
             is_active,
             NAME,
             seq,
             type,
             mt_btype_id)
VALUES      (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Airlines',
             0,
             'AIRLINES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'AEROSPACE_DEFENCE')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Defence',
             1,
             'DEFENCE',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'AEROSPACE_DEFENCE')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Agriculture Seeds',
             2,
             'AGRICULTURE_SEEDS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'AGRICULTURE')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Aquaculture',
             3,
             'AQUACULTURE',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'AGRICULTURE')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Dairy',
             4,
             'DAIRY',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'AGRICULTURE')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Fertilizers',
             5,
             'FERTILIZERS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'AGRICULTURE')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Food ingredients',
             6,
             'FOOD_INGREDIENTS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'AGRICULTURE')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Meat Products',
             7,
             'MEAT_PRODUCTS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'AGRICULTURE')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Poultry',
             8,
             'POULTRY',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'AGRICULTURE')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Private',
             9,
             'PRIVATE',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'BANK')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Public',
             10,
             'PUBLIC',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'BANK')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Development(Construction)',
             11,
             'DEVELOPMENT_CONSTRUCTION',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'CONSTRUCTION')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Development(Real Estate)',
             12,
             'DEVELOPMENT_REAL_ESTATE',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'CONSTRUCTION')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Real Estate Development and perations(Real Estate)',
             13,
             'REAL_ESTATE_DEVELOPMENT_OPERATIONS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'CONSTRUCTION')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Residential & Commercial Complexes(Construction)',
             14,
             'RESIDENTIAL_COMMERCIAL_COMPLEXES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'CONSTRUCTION')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Infrastructure(Construction)',
             15,
             'INFRASTRUCTURE',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'CONSTRUCTION')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Flat Sales(Construction)',
             16,
             'FLAT_SALES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'CONSTRUCTION')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Site Sales(Real Estate)',
             17,
             'SITE_SALES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'CONSTRUCTION')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Carbon Black',
             18,
             'CARBON_BLACK',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'CONSTRUCTION')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Cement',
             19,
             'CEMENT',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'CONSTRUCTION')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Marble',
             20,
             'MARBLE',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'CONSTRUCTION')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Granite',
             21,
             'GRANITE',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'CONSTRUCTION')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Sanitaryware',
             22,
             'SANITARYWARE',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'CONSTRUCTION')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'College',
             23,
             'COLLEGE',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'EDUCATION')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Labs & Life Sciences Services',
             24,
             'LABBS_LIFE_SCIENCES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'EDUCATION')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Online services',
             25,
             'ONLINE_SERVICES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'EDUCATION')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Schools',
             26,
             'SCHOOLS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'EDUCATION')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Acting',
             27,
             'ACTING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'ENTERTAINMENT')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Amusement Parks',
             28,
             'AMUSEMENT_PARKS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'ENTERTAINMENT')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Art',
             29,
             'ART',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'ENTERTAINMENT')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Club',
             30,
             'CLUB',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'ENTERTAINMENT')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Creative',
             31,
             'CREATIVE',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'ENTERTAINMENT')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Dance',
             32,
             'DANCE',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'ENTERTAINMENT')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Films',
             33,
             'FILMS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'ENTERTAINMENT')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Music',
             34,
             'MUSIC',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'ENTERTAINMENT')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Photography',
             35,
             'PHOTOGRAPHY',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'ENTERTAINMENT')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Production',
             36,
             'PRODUCTION',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'ENTERTAINMENT')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'ShortFilms',
             37,
             'SHORTFILMS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'ENTERTAINMENT')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Air Conditioners',
             38,
             'AIR_CONDITIONERS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'ELECTRONICS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Computer Peripherals',
             39,
             'COMPUTER_PERIPHERALS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'ELECTRONICS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Electric Equipment',
             40,
             'ELECTRIC_EQUIPMENT',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'ELECTRONICS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Boilers-Turbines(Electric Equipment)',
             41,
             'BOILERS_TURBINES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'ELECTRONICS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Switchgears(Electric Equipment)',
             42,
             'SWITCH_GEARS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'ELECTRONICS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Transformers(Electric Equipment)',
             43,
             'TRANSFORMERS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'ELECTRONICS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Electrodes & Electrical Equipments(Electric Equipment)',
             44,
             'ELECTRODES_ELECTRIC_EQUIPMENT',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'ELECTRONICS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Electrodes & Welding Equipment(Electric Equipment)',
             45,
             'ELECTRODES_WELDING_EQUIPMENT',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'ELECTRONICS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Electronic Goods(Electric Equipment)',
             46,
             'ELECTRONIC_GOODS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'ELECTRONICS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Watches & Accessories',
             47,
             'WATCHES_ACCESSORIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'ELECTRONICS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Electric stations',
             48,
             'ELECTRIC_STATIONS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'ENERGY')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Power Generation-Distribution',
             49,
             'POWER_GENERATION_DISTRIBUTION',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'ENERGY')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Renewables',
             50,
             'RENEWABLES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'ENERGY')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Fuel Stations',
             51,
             'FUEL_STATIONS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'ENERGY')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Beverages',
             52,
             'BEVERAGES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'FOOD')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Caterers',
             53,
             'CATERERS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'FOOD')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Consumer Food',
             54,
             'CONSUMER_FOOD',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'FOOD')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Distilleries',
             55,
             'DISTILLERIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'FOOD')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Groceries Shops',
             56,
             'GROCERIES_SHOPS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'FOOD')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Vegetable Oils & Products',
             57,
             'VEGETABLE_OILS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'FOOD')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Snacks',
             58,
             'SNACKS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'FOOD')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Biotechnology ',
             59,
             'BIOTECHNOLOGY',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'HEALTH_CARE')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Hospital & Healthcare Services',
             60,
             'HOSPITAL_HEALTHCARE',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'HEALTH_CARE')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Medical Research',
             61,
             'MEDICAL_RESEARCH',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'HEALTH_CARE')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Medical Stores',
             62,
             'MEDICAL_STORES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'HEALTH_CARE')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Opticals',
             63,
             'OPTICALS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'HEALTH_CARE')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Hotel',
             64,
             'HOTEL',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'HOSPITALITY')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Resort',
             65,
             'RESORT',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'HOSPITALITY')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Restaurants',
             66,
             'RESTAURANTS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'HOSPITALITY')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Abrasives',
             67,
             'ABRASIVES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Aluminium & Aluminium Products',
             68,
             'ALUMINIUM_PRODUCTS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Auto Ancillaries',
             69,
             'AUTO_ANCILLARIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Air Conditioning Parts(Auto Ancillaries)',
             70,
             'AIR_CONDITIONING_AUTO_ANCILLARIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Auto, Truck & Motorcycle Parts(Auto Ancillaries)',
             71,
             'AUTO_TRUCK_MOTORCYCLE_AUTO_ANCILLARIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Axle shafts(Auto Ancillaries)',
             72,
             'AXLE_AUTO_ANCILLARIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Bearings(Auto Ancillaries)',
             73,
             'BEARINGS_AUTO_ANCILLARIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Brakes(Auto Ancillaries)',
             74,
             'BRAKES_AUTO_ANCILLARIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Bus Body(Auto Ancillaries)',
             75,
             'BUSBODY_AUTO_ANCILLARIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Castings/Forgings(Auto Ancillaries)',
             76,
             'CASTINGS_FORGINGS_AUTO_ANCILLARIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Cycles & Bicycles',
             77,
             'CYCLES_BICYCLES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Clutches(Auto Ancillaries)',
             78,
             'CLUTCHES_AUTO_ANCILLARIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Diesel Engines(Auto Ancillaries)',
             79,
             'DIESEL_ENGINES_AUTO_ANCILLARIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Engine Parts(Auto Ancillaries)',
             80,
             'ENGINE_PARTS_AUTO_ANCILLARIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Gears(Auto Ancillaries)',
             81,
             'GEARS_AUTO_ANCILLARIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Head lamps & lights(Auto Ancillaries)',
             82,
             'HEAD_LAMPS_AUTO_ANCILLARIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Pistons(Auto Ancillaries)',
             83,
             'PISTONS_AUTO_ANCILLARIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Seating covers & parts(Auto Ancillaries)',
             84,
             'SEATING_COVERS_AUTO_ANCILLARIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Sheet Metals(Auto Ancillaries)',
             85,
             'SHEET_METALS_AUTO_ANCILLARIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Shock absorbers(Auto Ancillaries)',
             86,
             'SHOCK_ABSORBERS_AUTO_ANCILLARIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Spare Parts & Accessories(Auto Ancillaries)',
             87,
             'SPARE_ACCESSORIES_AUTO_ANCILLARIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Springs(Auto Ancillaries)',
             88,
             'SPRINGS_AUTO_ANCILLARIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Tyres & Rubber Products(Auto Ancillaries)',
             89,
             'TYRES_AUTO_ANCILLARIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Wheels(Auto Ancillaries)',
             90,
             'WHEELS_AUTO_ANCILLARIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Auto & Truck Manufacturers',
             91,
             'AUTO_TRUCK_AUTO_ANCILLARIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Dealers & Distributors',
             92,
             'DEALERS_DISTRIBUTORS_AUTO_ANCILLARIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'LCVS/ HVCS',
             93,
             'LCVS_HVCS_AUTO_ANCILLARIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Passenger Cars',
             94,
             'PASSENGER_AUTO_ANCILLARIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Tractors',
             95,
             'TRACTORS_AUTO_ANCILLARIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Trucks/LCVs',
             96,
             'TRUCKS_LCVS_AUTO_ANCILLARIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Batteries',
             97,
             'BATTERIES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Compressors / Pumps',
             98,
             'COMPRESSORS_PUMPS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Containers & Packaging',
             99,
             'CONTAINERS_PACKAGING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Detergents & Soaps',
             100,
             'DETERGENTS_SOAPS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Footwear',
             101,
             'FOOTWARE',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Leather',
             102,
             'LEATHER',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Plastic Products',
             103,
             'PLASTIC_PRODUCTS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Moulded Articles and Furnitures',
             104,
             'MOULDED_ARTICLES_PLASTIC_PRODUCTS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Pet Bottels, Jars & Containers',
             105,
             'PET_BOTTELS_JARS_CONTAINERS_PLASTIC_PRODUCTS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Self Adhesive Tapes',
             106,
             'SELF_ADHESIVE_TAPES_PLASTIC_PRODUCTS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Thermoplastics',
             107,
             'THERMOPLASTICS_PLASTIC_PRODUCTS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Tubes/Pipes/Hoses & Fittings',
             108,
             'TUBES_PIPES_HOSES_PLASTIC_PRODUCTS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Rubber Products',
             109,
             'RUBBER_PRODUCTS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Wood & Wood Products',
             110,
             'WOOD_WOOD_PRODUCTS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MANUFACTURING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'jewellery',
             111,
             'JEWELLERY',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'METALS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Coal ',
             112,
             'COAL',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MINING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Specialty Mining & Metals',
             113,
             'SPECIALTY_MINING_METALS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'MINING')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Cable & D2H',
             114,
             'CABLE_DH',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'NEWS_MEDIA')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Printing & Publishing',
             115,
             'PRINTING_PUBLISHING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'NEWS_MEDIA')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Printing & Stationery',
             116,
             'PRINTING_STATIONERY',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'NEWS_MEDIA')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Social media',
             117,
             'SOCIAL_MEDIA',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'NEWS_MEDIA')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'NGO-helping hand',
             118,
             'NGO',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'NGO')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Gas',
             119,
             'GAS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'OIL_NATURALGAS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Oil',
             120,
             'OIL',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'OIL_NATURALGAS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Soap',
             121,
             'SOAP',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'PHARMACEUTICAL')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Speciality Chemicals',
             122,
             'SPECIALITY_CHEMICALS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'PHARMACEUTICAL')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Paints',
             123,
             'PAINTS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'PHARMACEUTICAL')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Forest Products',
             124,
             'FOREST_PRODUCTS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'PHARMACEUTICAL')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Pesticides & Agrochemicals',
             125,
             'PESTICIDES_AGROCHEMICALS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'PHARMACEUTICAL')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Pharmaceuticals & Drugs ',
             126,
             'PHARMACEUTICALS_DRUGS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'PHARMACEUTICAL')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'BPO/ITeS',
             127,
             'BPO_ITES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SOFTWARE')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'IT-Education',
             128,
             'IT_EDUCATION',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SOFTWARE')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'IT-Networking',
             129,
             'IT_NETWORKING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SOFTWARE')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'IT Services & Consulting',
             130,
             'IT_SERVICES_CONSULTING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SOFTWARE')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Archery',
             131,
             'ARCHERY',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Badminton',
             132,
             'BADMINTON',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Basketball',
             133,
             'BASKETBALL',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Boxing',
             134,
             'BOXING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Bungee jumping',
             135,
             'BUNGEE_JUMPING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Circket',
             136,
             'CIRCKET',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Cycling',
             137,
             'CYCLING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Darts',
             138,
             'DARTS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Fencing',
             139,
             'FENCING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Figure skating',
             140,
             'FIGURE_SKATING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Fishing',
             141,
             'FISHING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Football',
             142,
             'FOOTBALL',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Golf',
             143,
             'GOLF',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'GYM',
             144,
             'GYM',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Gymnastics',
             145,
             'GYMNASTICS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Handball',
             146,
             'HANDBALL',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Hang gliding',
             147,
             'HANG_GLIDING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Hockey',
             148,
             'HOCKEY',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Horse racing',
             149,
             'HORCE_RACING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Ice Hockey',
             150,
             'ICE_HOCKEY',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Ice Hockey Skating',
             151,
             'ICE_SKATING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Indore games',
             152,
             'INDORE_GAMES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Jet skiing',
             153,
             'JET_SKIING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Judo',
             154,
             'JUDO',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Karate',
             155,
             'KARATE',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Kick boxing',
             156,
             'KICKBOXING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Rock climbing',
             157,
             'ROCK_CLIMBING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Roller skating',
             158,
             'ROLLER_SKATING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Running',
             159,
             'RUNNING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Rugby',
             160,
             'RUGBY',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Sky diving',
             161,
             'SKY_DIVING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Scuba diving',
             162,
             'SCUBA_DIVING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Sumo wrestling',
             163,
             'SUMO_WRESTLING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Skate boarding',
             164,
             'SKATE_BOARDING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Snow boarding',
             165,
             'SNOW_BOARDING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Surfing',
             166,
             'SURFING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Swimming',
             167,
             'SWIMMING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Table tennis',
             168,
             'TABLE_TENNIS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Tennis',
             169,
             'TENNIS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Taekwondo',
             170,
             'TAEKWONDO',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Volleyball',
             171,
             'VOLLERBALL',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Weight lifting',
             172,
             'WEIGHT_LIFTING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Wind surfing',
             173,
             'WIND_SURFING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'SPORTS')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Courier',
             174,
             'COURIER',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'TRANSPORT')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Ship Building',
             175,
             'SHIP_BUILDING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'TRANSPORT')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Shipping',
             176,
             'SHIPPING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'TRANSPORT')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Transport Infrastructure',
             177,
             'TRANSPORT_INFRASTRUCTURE',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'TRANSPORT')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Travel Services',
             178,
             'TRAVEL_SERVICES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'TRANSPORT')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Telecommunication-Equipment',
             179,
             'TELE_EQUIPMENT',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'TELECOMMUNICATION')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Telecommunication-Service Provider',
             180,
             'TELE_SERVICE_PROVIDER',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'TELECOMMUNICATION')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Telecommunications Services',
             181,
             'TELE_SERVICES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'TELECOMMUNICATION')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Textile-Spinning',
             182,
             'TEXTILE_SPINNING',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'TEXTILE')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Textiles',
             183,
             'TEXTILES',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'TEXTILE')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Textiles & Apparel',
             184,
             'TEXTILES_APPAREL',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'TEXTILE')),
            (Nextval('mt_sub_btype_id_seq'),
             Now(),
             Now(),
             true,
             'Others',
             185,
             'OTHERS',
             (SELECT id
              FROM   mt_btype
              WHERE  type = 'OTHERS'));